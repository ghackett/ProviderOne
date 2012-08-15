class ApplicationController < ActionController::Base
  protect_from_forgery

  helper_method :save_db_and_generate_upload_id, :insert_update_algorithms, :options_from_array_for_select, :generate_upload_id, :valid_column_types

  def save_db_and_generate_upload_id(datafile)
    upload_id = generate_upload_id
    path = DatabaseInfo.get_sqlite_path(upload_id)
    File.open(path, "wb") { |f| f.write(datafile.read) }
    return upload_id
  end

  def generate_upload_id()
    return "%10.6f" % Time.new.to_f + "." + UUID.new.generate
  end

  def insert_update_algorithms
  [
      "CONFLICT_NONE",
      "CONFLICT_ROLLBACK",
      "CONFLICT_ABORT",
      "CONFLICT_FAIL",
      "CONFLICT_IGNORE",
      "CONFLICT_REPLACE"
  ]
  end
  
  def valid_column_types
  [
      "string",
      "integer",
      "double",
      "boolean",
      "float",
      "long",
      "char",
      "blob",
      "timestamp"
  ]
  end

  def options_from_array_for_select(array, selected)
    rtr = ""
    array.each do |opt|
      rtr += "<option value=\"#{opt}\" "
      if (opt == selected)
        rtr += "selected"
      end
      rtr += ">#{opt}</option>"
    end
    return rtr.html_safe
  end

end
