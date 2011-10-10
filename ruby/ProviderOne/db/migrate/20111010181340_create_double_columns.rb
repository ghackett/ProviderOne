class CreateDoubleColumns < ActiveRecord::Migration
  def change
    create_table :double_columns do |t|

      t.timestamps
    end
  end
end
