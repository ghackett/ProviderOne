        byte[] {LowerName}_temp = (byte[]) in.readValue(byte[].class.getClassLoader());
        m{CapCamelName} = {LowerName}_temp == null ? null : ByteBuffer.wrap({LowerName}_temp);
