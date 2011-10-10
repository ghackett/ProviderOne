class CreateTableInfos < ActiveRecord::Migration
  def change
    create_table :table_infos do |t|

      t.timestamps
    end
  end
end
