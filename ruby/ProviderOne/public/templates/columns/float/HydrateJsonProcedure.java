		if (obj.has({CapCamelTableName}Info.Columns.{CapName})) {
		    try {
		        m{CapCamelName} = (float)obj.getDouble({CapCamelTableName}Info.Columns.{CapName});
		    } catch (JSONException e) {
		        m{CapCamelName} = null;
		    }
		    m{CapCamelName}Set = true;
		}
