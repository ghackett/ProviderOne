class CreateDatabaseInfos < ActiveRecord::Migration
  def change
    create_table :database_infos do |t|

      t.timestamps
    end
  end
end
