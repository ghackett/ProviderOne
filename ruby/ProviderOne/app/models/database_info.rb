require 'rubygems'
require 'sqlite3'
require 'xmlsimple'

class DatabaseInfo
  attr_accessor :filepath, :filename, :tables, :indecies, :upload_id, :package, :content_authority, :version

  def initialize(fname, upload_id)
    @upload_id = upload_id
    @filepath = DatabaseInfo.get_sqlite_path(@upload_id)
    @filename = fname
    @tables = Hash.new()
    @indecies = []

    db = SQLite3::Database.new(@filepath)

    db.results_as_hash = true

    db.execute( "select * from sqlite_master" ) do |row|
      name = row[:name.to_s]
      type = row[:type.to_s]
      tbl_name = row[:tbl_name.to_s]
      sql = row[:sql.to_s]

      if (! name.to_s.starts_with? "sqlite")
        if (type.downcase == "table")
          @tables[name] = TableInfo.new(name, sql, db.table_info(name))
        elsif (type.downcase == "index")
          @indecies << IndexInfo.new(name, tbl_name, sql)
        end
      end

    end

  end

  def to_s
    rtr = "\n***************\nDatabase: #{@filename} at #{@filepath}\nPackage Name: #{@package}\nContentAuthority: #{@content_authority}\n"
    @tables.each_value do |info|
      rtr += info.to_s
    end

    @indecies.each do |index|
      rtr += index.to_s
    end
    rtr += "***************\n\n"
    return rtr
  end





  def self.get_sqlite_path(upload_id)
    path = "tmp/sqltmp/"

    if (!Dir.exists?(path))
      Dir.mkdir(path)
    end

    path += upload_id + ".sqlite"
    return path
  end

  def to_simple_xml
    data = Hash.new
    data['filename'] = @filename
    data['package'] = @package
    data['content_authority'] = @content_authority
    data['version'] = @version

    lookup_keys = []
    @tables.each_value do |table|
      lookup_keys << {'table' => table.name, 'key' => table.get_lookup_column.name}
    end

    return XmlSimple.xml_out({"database" => data, "lookup_key" => lookup_keys})
  end

  def from_xml(xml)
    hsh = XmlSimple.xml_in(xml)
    data = hsh['database'].first
    lookup_keys = hsh['lookup_key']

    @filename = data['filename']
    @package = data['package']
    @content_authority = data['content_authority']
    @version = Integer(data['version']) +1

    lookup_keys.each do |lk|
      @tables[lk['table']].set_lookup_column(lk['key'])
    end
  end

  def from_params(params)
    dbparams = params[:dbinfo]
    @package = dbparams[:package]
    @content_authority = dbparams[:content_authority]
    @version = dbparams[:version]

    @tables.each_value do |table|
      table.set_lookup_column(params["lookup_key_" + table.name])
    end
  end

end
