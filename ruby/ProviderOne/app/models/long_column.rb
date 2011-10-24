class LongColumn < ColumnInfo

    def initialize(name, type)
      super(name, type)
      @java_type = "Long"
    end
end
