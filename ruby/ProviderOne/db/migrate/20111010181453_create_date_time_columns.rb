class CreateDateTimeColumns < ActiveRecord::Migration
  def change
    create_table :date_time_columns do |t|

      t.timestamps
    end
  end
end
