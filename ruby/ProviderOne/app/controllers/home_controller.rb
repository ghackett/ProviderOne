require 'rubygems'
require 'uuid'

class HomeController < ApplicationController

  attr_accessor :dbinfo

  def index
  end
  
  def uploadFile
    upload = params[:upload1]
    datafile = upload['datafile']

    path = "public/sqltmp/" + "%10.6f" % Time.new.to_f + "." + UUID.new.generate + ".sqlite"
    File.open(path, "wb") { |f| f.write(datafile.read) }

    original_name = datafile.original_filename
    @dbinfo = DatabaseInfo.new(original_name, path)
  end

end
