class CreateBlobColumns < ActiveRecord::Migration
  def change
    create_table :blob_columns do |t|

      t.timestamps
    end
  end
end
