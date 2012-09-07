        if (h.col_{LowerName} != -1) {
            m{CapCamelName} = null;
            if (!c.isNull(h.col_{LowerName})) {
                String {CamelName} = c.getString(h.col_{LowerName});
                if ({CamelName}.length() > 0)
                    m{CapCamelName} = {CamelName}.charAt(0);
            }
            m{CapCamelName}Set = true;
        } else {
            m{CapCamelName} = null;
            m{CapCamelName}Set = false;
        }
