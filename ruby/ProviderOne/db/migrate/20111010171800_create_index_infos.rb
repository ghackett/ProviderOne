class CreateIndexInfos < ActiveRecord::Migration
  def change
    create_table :index_infos do |t|

      t.timestamps
    end
  end
end
