class CreateBoolColumns < ActiveRecord::Migration
  def change
    create_table :bool_columns do |t|

      t.timestamps
    end
  end
end
