		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
		        m{CapCamelName} = obj.get{JavaType}({CapCamelTableName}Info.Columns.{CapName});
				if (NULL.equalsIgnoreCase(m{CapCamelName}))
					m{CapCamelName} = null;
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
