require 'calabash-android/abase'
require 'calabash-android/calabash_steps'

class MainPage < Calabash::ABase

  def click_card_navigator
    tap_when_element_exists "* id:'card_navigator'"
  end

  def click_filter_action
    tap_when_element_exists "* id:'action_filter'"
  end
end