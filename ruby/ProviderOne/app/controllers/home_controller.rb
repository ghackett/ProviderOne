require 'rubygems'
require 'uuid'
require 'zip/zip'
require 'zip/zipfilesystem'

class HomeController < ApplicationController

  attr_accessor :dbinfo

  def index
  end
  
  def options
    upload = params[:upload1]
    datafile = upload['datafile']
    original_name = datafile.original_filename

    filetype = upload['filetype']

    if (filetype == "binary")
      @dbinfo = DatabaseInfo.new(original_name, save_db_and_generate_upload_id(datafile))
    else
      @dbinfo = DatabaseInfo.create_db_and_execute_sql(datafile.read, generate_upload_id)
    end


    upload2 = params[:upload2]
    if (upload2 != nil)
      xmlfile = upload2['datafile']
      if (xmlfile != nil)
        #hsh = XmlSimple.xml_in(xmlfile.read)
        #render :text => hsh.to_s
        @dbinfo.from_xml(xmlfile.read)
      end
    end
  end




  def download
    dbparams = params[:dbinfo]

    @dbinfo = DatabaseInfo.new(dbparams[:filename], dbparams[:upload_id])
    @dbinfo.from_params(params)

    t = Tempfile.new("tzip-" + @dbinfo.upload_id)
    Zip::ZipOutputStream.open(t.path) do |z|
      z.put_next_entry('config/ProviderOneConfig.xml')
      z.write(@dbinfo.to_xml)

      z.put_next_entry("config/" + @dbinfo.filename)
      z.write(File.read(@dbinfo.filepath))

      #removed ArrayUtils from sample project
      #z.put_next_entry("java/database/autogen/util/ArrayUtils.java")
      #z.write(@dbinfo.process_file_content(File.read("public/templates/utils/ArrayUtils.java")))

      z.put_next_entry("java/database/autogen/util/PlatformDatabaseUtils.java")
      z.write(@dbinfo.process_file_content(File.read("public/templates/utils/PlatformDatabaseUtils.java")))

      z.put_next_entry("java/database/autogen/util/SelectionBuilder.java")
      z.write(@dbinfo.process_file_content(File.read("public/templates/utils/SelectionBuilder.java")))

      z.put_next_entry("java/database/autogen/PersistentObject.java")
      z.write(@dbinfo.process_file_content(File.read("public/templates/autogen/PersistentObject.java")))

      z.put_next_entry("java/database/autogen/#{@dbinfo.project_name}Database.java")
      z.write(@dbinfo.get_database_java)

      z.put_next_entry("java/database/autogen/Base#{@dbinfo.project_name}Provider.java")
      z.write(@dbinfo.get_base_provider)

      z.put_next_entry("java/database/#{@dbinfo.project_name}Provider.java")
      z.write(@dbinfo.process_file_content(File.read("public/templates/autogen/Provider.java")))

      @dbinfo.tables.each_value do |tbl|
        z.put_next_entry("java/database/autogen/tables/Base#{tbl.cap_camel_name}Info.java")
        z.write(tbl.get_base_table_info_content(@dbinfo))

        z.put_next_entry("java/database/tables/#{tbl.cap_camel_name}Info.java")
        z.write(tbl.process_file_content(File.read("public/templates/tables/TableInfo.java"), @dbinfo))

        z.put_next_entry("java/database/autogen/objects/Base#{tbl.cap_camel_name}.java")
        z.write(tbl.get_base_table_object(@dbinfo))

        z.put_next_entry("java/database/objects/#{tbl.cap_camel_name}.java")
        z.write(tbl.process_file_content(File.read("public/templates/tables/Table.java"), @dbinfo))

      end
    end

    send_file(t.path, :type => "application/zip", :disposition => "attachment", :filename => "ProviderOnePackage.zip")
    t.close
  end




end
