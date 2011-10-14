class ColumnInfo

  attr_accessor :name, :type, :is_lookup_key, :camel_name, :cap_camel_name

  def initialize(name, type)
    @name = name;
    @type = type;
    @camel_name = @name.camelize
    @camel_name[0] = @camel_name.first.downcase
    @cap_camel_name = @name.camelize
    @is_lookup_key = false
  end

  def to_s
    rtr = "COLUMN #{@name} OF TYPE #{type} - #{self.class.to_s} - #{@camel_name} - #{@cap_camel_name}"
    if @is_lookup_key
      rtr += " - LOOKUPKEY"
    end
    return rtr
  end

  #def to_hash
  #  return {"name" => @name, "is_lookup_key" => @is_lookup_key}
  #end
  #
  #def from_hash(hash)
  #  if (@name == hash['name'])
  #    @is_lookup_key = (hash['is_lookup_key'] == "true")
  #  end
  #end

  def self.get_column(col_info)
    name = col_info['name'].downcase
    type = col_info['type'].downcase

    if name == "_id"
      return IdColumn.new(name, type);
    end

    case type
      when "int", "integer"
        return IntColumn.new(name, type)
      when "string", "text", "varchar", "clob"
        return TextColumn.new(name, type)
      when "double", "real", "numeric"
        return DoubleColumn.new(name, type)
      when "bool", "boolean"
        return BoolColumn.new(name, type)
      when "float"
        return FloatColumn.new(name, type)
      when "long"
        return LongColumn.new(name, type)
      when "char", "character"
        return CharColumn.new(name, type)
      when "blob"
        return BlobColumn.new(name, type)
      when "datetime", "date", "timestamp"
        return DateTimeColumn.new(name, type)
    end
    return nil
  end

end
