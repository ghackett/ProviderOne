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
      z.write(@dbinfo.to_simple_xml)

      z.put_next_entry("config/" + @dbinfo.filename)
      z.write(File.read(@dbinfo.filepath))
    end

    send_file(t.path, :type => "application/zip", :disposition => "attachment", :filename => "ProviderOnePackage.zip")
    t.close
  end




end
