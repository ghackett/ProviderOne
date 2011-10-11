class ApplicationController < ActionController::Base
  protect_from_forgery

  helper_method :save_db_and_generate_upload_id, :get_sqlite_path

  def save_db_and_generate_upload_id(datafile)
    upload_id = "%10.6f" % Time.new.to_f + "." + UUID.new.generate
    path = DatabaseInfo.get_sqlite_path(upload_id)
    File.open(path, "wb") { |f| f.write(datafile.read) }
    return upload_id
  end

end
