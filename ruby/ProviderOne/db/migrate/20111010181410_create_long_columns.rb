class CreateLongColumns < ActiveRecord::Migration
  def change
    create_table :long_columns do |t|

      t.timestamps
    end
  end
end
