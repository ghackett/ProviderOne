        if (h.col_{LowerName} != -1) {
            m{CapCamelName} = c.isNull(h.col_{LowerName}) ? null : c.getInt(h.col_{LowerName});
            m{CapCamelName}Set = true;
        } else {
            m{CapCamelName} = null;
            m{CapCamelName}Set = false;
        }
