class DateTimeColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Long"
    end

    def get_imports
       return ["java.util.Date"]
    end
end
