class CreateColumnInfos < ActiveRecord::Migration
  def change
    create_table :column_infos do |t|

      t.timestamps
    end
  end
end
