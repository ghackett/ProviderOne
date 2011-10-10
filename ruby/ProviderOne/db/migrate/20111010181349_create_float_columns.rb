class CreateFloatColumns < ActiveRecord::Migration
  def change
    create_table :float_columns do |t|

      t.timestamps
    end
  end
end
