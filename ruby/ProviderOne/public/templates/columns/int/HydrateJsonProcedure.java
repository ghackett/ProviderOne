		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
		        m{CapCamelName} = obj.getInt({CapCamelTableName}Info.Columns.{CapName});
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
