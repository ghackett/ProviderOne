		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
		        m{CapCamelName} = obj.get{JavaType}({CapCamelTableName}Info.Columns.{CapName});
		    } catch (JSONException e) {
		        m{CapCamelName} = false;
		    }
		    m{CapCamelName}Set = true;
		}
