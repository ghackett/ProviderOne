class FloatColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Float"
    end

    def get_add_to_json_values(table)
      defs = "\t\tif (m#{@cap_camel_name}Set && h.col_#{@lower_name} != -1)\n\t\t\trtr.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, m#{@cap_camel_name}.doubleValue);\n"
      return defs
    end
end
