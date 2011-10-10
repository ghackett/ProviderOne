class ColumnInfo < ActiveRecord::Base

  attr_accessor :name, :type, :is_lookup_key

  def initialize(name, type)
    @name = name;
    @type = type;
  end

  def to_s
    return "COLUMN #{@name} OF TYPE #{type} - #{self.class.to_s}"
  end

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
    return ColumnInfo.new(name, type)
  end

end
