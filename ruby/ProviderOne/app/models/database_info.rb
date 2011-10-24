require 'rubygems'
require 'sqlite3'
require 'xmlsimple'

class DatabaseInfo
  attr_accessor :filepath, :filename, :tables, :indecies, :upload_id, :package, :content_authority, :version, :project_name

  def initialize(fname, upload_id)
    @upload_id = upload_id
    @filepath = DatabaseInfo.get_sqlite_path(@upload_id)
    @filename = fname
    @tables = Hash.new()
    @indecies = []
    @version = 1
    @project_name = "SampleProject"

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

    db.close

  end


  def process_file_content(file_content)
    file_content = file_content.gsub("{PackageName}", @package);
    file_content = file_content.gsub("{DbFileName}", @filename);
    file_content = file_content.gsub("{ContentAuthority}", @content_authority);
    file_content = file_content.gsub("{DbVersion}", @version.to_s);
    file_content = file_content.gsub("{ProjectName}", @project_name);
    return file_content
  end

  def get_database_java()
    file_content = File.read("public/templates/autogen/Database.java")

    table_info_imports = ""
    table_creates = ""
    table_upgrades = ""
    index_defs = ""
    index_exes = ""

    @tables.each_value do |tbl|
      table_info_imports += "import #{@package}.database.tables.#{tbl.cap_camel_name}Info;\n"
      table_creates += "\t\t#{tbl.cap_camel_name}Info.createTable(db);\n"
      table_upgrades += "\t\t#{tbl.cap_camel_name}Info.upgradeTable(db, oldVersion, newVersion);\n"
    end

    @indecies.each do |idx|
      index_defs += "\tpublic static final String IDX_CREATE_#{idx.cap_name} = \"#{idx.create_stmt}\";\n"
      index_defs += "\tpublic static final String IDX_DROP_#{idx.cap_name} = \"#{idx.drop_stmt}\";\n"
      index_exes += "\t\tdb.execSQL(IDX_DROP_#{idx.cap_name});\n"
      index_exes += "\t\tdb.execSQL(IDX_CREATE_#{idx.cap_name});\n"
    end

    file_content = file_content.gsub("{TableInfoImports}", table_info_imports);
    file_content = file_content.gsub("{IndexDefs}", index_defs);
    file_content = file_content.gsub("{TableCreates}", table_creates);
    file_content = file_content.gsub("{TableUpgrades}", table_upgrades);
    file_content = file_content.gsub("{IndexExecutes}", index_exes);

    return process_file_content(file_content)
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

  def self.create_db_and_execute_sql(sql, upload_id)
    path = DatabaseInfo.get_sqlite_path(upload_id)
    db = SQLite3::Database.new(path)
    db.execute_batch(sql)
    db.close
    return DatabaseInfo.new("my_database.sqlite", upload_id)
  end

  def to_xml
    data = Hash.new
    data['filename'] = @filename
    data['package'] = @package
    data['content_authority'] = @content_authority
    data['version'] = @version
    data['project_name'] = @project_name

    tables = []
    @tables.each_value do |table|
      tables << table.to_hash
    end

    data['table'] = tables

    return XmlSimple.xml_out({"database" => data})
  end

  def from_xml(xml)
    hsh = XmlSimple.xml_in(xml)
    data = hsh['database'].first
    tables = data['table']

    @filename = data['filename']
    @package = data['package']
    @content_authority = data['content_authority']
    @version = Integer(data['version']) +1
    @project_name = data['project_name']

    tables.each do |xtbl|
      table = @tables[xtbl['name']]
      table.from_hash(xtbl)
      #table.set_lookup_column(lk['key'])
    end
  end

  def from_params(params)
    dbparams = params[:dbinfo]
    @package = dbparams[:package]
    @content_authority = dbparams[:content_authority]
    @version = dbparams[:version]
    @project_name = dbparams[:project_name]

    @tables.each_value do |table|
      table.set_lookup_column(params["lookup_key_" + table.name])
      table.update_algorithm = params["update_algorithm_" + table.name]
      table.insert_algorithm = params["insert_algorithm_" + table.name]
    end
  end

end
