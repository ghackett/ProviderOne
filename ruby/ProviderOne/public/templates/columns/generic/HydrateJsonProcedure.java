		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
				if (obj.isNull({CapCamelTableName}Info.Columns.{CapName})) {
					m{CapCamelName} = null;
				} else {
		        	m{CapCamelName} = obj.get{JavaType}({CapCamelTableName}Info.Columns.{CapName});
				}
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
