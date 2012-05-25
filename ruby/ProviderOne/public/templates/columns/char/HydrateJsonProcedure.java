		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
			String {CamelName} = null;
		    try {
		        {CamelName} = obj.getString({CapCamelTableName}Info.Columns.{CapName});
				if ({CamelName} == null || {CamelName}.length() == 0)
					m{CapCamelName} = null;
				else
					m{CapCamelName} = {CamelName}.charAt(0);
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
