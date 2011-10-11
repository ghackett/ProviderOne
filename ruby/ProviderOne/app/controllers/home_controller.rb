require 'rubygems'
require 'uuid'

class HomeController < ApplicationController

  attr_accessor :dbinfo

  def index
  end
  
  def uploadFile
    upload = params[:upload1]
    datafile = upload['datafile']
    original_name = datafile.original_filename

    @dbinfo = DatabaseInfo.new(original_name, save_db_and_generate_upload_id(datafile))
  end




  def submitForm
    dbparams = params[:dbinfo]
    @dbinfo = DatabaseInfo.new(dbparams[:filename], dbparams[:upload_id])
    @dbinfo.package = dbparams[:package]
    @dbinfo.content_authority = dbparams[:content_authority]

    @dbinfo.tables.each_value do |table|
      table.set_lookup_column(params["lookup_key_" + table.name])
    end
  end




end
