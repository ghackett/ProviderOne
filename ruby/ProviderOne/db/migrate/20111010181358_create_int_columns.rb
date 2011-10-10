class CreateIntColumns < ActiveRecord::Migration
  def change
    create_table :int_columns do |t|

      t.timestamps
    end
  end
end
