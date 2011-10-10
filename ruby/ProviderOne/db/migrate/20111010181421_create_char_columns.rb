class CreateCharColumns < ActiveRecord::Migration
  def change
    create_table :char_columns do |t|

      t.timestamps
    end
  end
end
