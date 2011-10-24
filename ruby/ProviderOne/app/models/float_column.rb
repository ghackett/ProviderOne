class FloatColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Float"
    end
end
