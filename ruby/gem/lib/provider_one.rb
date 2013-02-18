class ProviderOne
  def self.template_dir
    return File.expand_path('../provider_one/templates', __FILE__)
  end
  
  def self.hi(name)
    puts "Hello #{name}, I will print out the readme template now."
  end
  def self.test
    puts File.read("#{ProviderOne.template_dir}/Readme.md")
  end
end