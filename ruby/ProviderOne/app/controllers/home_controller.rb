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

  end




end
