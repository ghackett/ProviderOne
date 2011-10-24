class DoubleColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Double"
    end
end
