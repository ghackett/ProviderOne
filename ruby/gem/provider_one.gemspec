Gem::Specification.new do |s|
  s.name        = 'provider_one'
  s.version     = '0.0.0'
  s.executables << 'provider_one'
  s.date        = '2012-09-05'
  s.summary     = "A new kind of ORM for Android (not yet functional)"
  s.description = "Take the pain out of dealing with databases in Android by generating fully functional database, content provider, and ORM object code, from nothing more than a pre-built sqlite database or a .sql file."
  
  s.add_dependency('xml-simple', '>= 1.1.0')
  s.add_dependency('sqlite3', '>= 1.3.4')
  s.add_dependency('rubyzip', '>= 0.9.4')
  s.add_dependency('uuid', '>= 2.3.4')
  
  s.authors     = ["Geoff Hackett"]
  s.email       = 'ghackett@gmail.com'
  s.files       = ["lib/provider_one.rb"]
  s.homepage    =
    'http://rubygems.org/gems/provider_one'
end