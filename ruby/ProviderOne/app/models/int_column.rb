class IntColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Integer"
    end
end
