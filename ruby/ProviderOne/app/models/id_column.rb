class IdColumn < ColumnInfo

  def initialize(name, type)
    super(name, type)
    @camel_name = "Id"
  end
end
