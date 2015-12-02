require 'calabash-android/abase'
require 'calabash-android/calabash_steps'

class MainPage < Calabash::ABase

  def click_card_navigator
    tap_when_element_exists "* id:'card_navigator'"
  end

  def filter_action_should_appear
    tap_when_element_exists "* id:'action_filter'"
  end
end