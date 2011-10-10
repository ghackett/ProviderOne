class CreateIdColumns < ActiveRecord::Migration
  def change
    create_table :id_columns do |t|

      t.timestamps
    end
  end
end
