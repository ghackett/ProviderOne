class CreateTextColumns < ActiveRecord::Migration
  def change
    create_table :text_columns do |t|

      t.timestamps
    end
  end
end
