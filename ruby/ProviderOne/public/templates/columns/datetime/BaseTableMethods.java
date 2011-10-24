   public Long get{CapCamelName}InMillis() {
       return m{CapCamelName};
   }

   public Integer get{CapCamelName}InSeconds() {
       if (m{CapCamelName} == null)
           return null;
       return (int)(m{CapCamelName}.longValue()/1000);
   }

   public Date get{CapCamelName}AsDate() {
       if (m{CapCamelName} == null)
           return null;
       return new Date(m{CapCamelName});
   }

   public void set{CapCamelName}InMillis(Long {CamelName}) {
       m{CapCamelName} = {CamelName};
       m{CapCamelName}Set = true;
   }

   public void set{CapCamelName}InSeconds(Integer {CamelName}) {
       if ({CamelName} == null) {
           m{CapCamelName} = null;
           return;
       }
       m{CapCamelName} = {CamelName}.longValue() * 1000;
   }

   public void set{CapCamelName}AsDate(Date {CamelName}) {
       if ({CamelName} == null) {
           m{CapCamelName} = null;
           return;
       } else {
           m{CapCamelName} = {CamelName}.getTime();
       }
   }
