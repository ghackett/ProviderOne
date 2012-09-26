		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
				if (obj.isNull({CapCamelTableName}Info.Columns.{CapName})) {
		        	m{CapCamelName} = null;
				} else {
					String {CamelName} = obj.getString({CapCamelTableName}Info.Columns.{CapName});
					if ({CamelName} == null || {CamelName}.length() == 0)
						m{CapCamelName} = null;
					else
						m{CapCamelName} = {CamelName}.charAt(0);
			        
				}
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
