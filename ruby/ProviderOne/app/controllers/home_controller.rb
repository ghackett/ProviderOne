require 'rubygems'
require 'uuid'

class HomeController < ApplicationController

  attr_accessor :dbinfo

  def index
  end
  
  def uploadFile
    upload = params[:upload1]
    datafile = upload['datafile']
    path = "tmp/sqltmp/"
    if (!Dir.exists?(path))
      Dir.mkdir(path)
    end
    path += "%10.6f" % Time.new.to_f + "." + UUID.new.generate + ".sqlite"
    File.open(path, "wb") { |f| f.write(datafile.read) }

    original_name = datafile.original_filename
    @dbinfo = DatabaseInfo.new(original_name, path)
  end

end
