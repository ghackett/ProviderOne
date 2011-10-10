class HomeController < ApplicationController
  def index
  end
  
  def uploadFile
    upload = params[:upload1]
    path = "public/sqltmp/" + "%10.6f" % Time.new.to_f + ".sqlite"
    File.new(path, "wb") { |f| f.write(upload['datafile'].read) }
  end

end
