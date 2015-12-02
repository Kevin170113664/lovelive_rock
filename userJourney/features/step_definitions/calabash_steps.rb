require 'calabash-android/calabash_steps'

When(/^I click card navigator$/) do
  @main_page.click_card_navigator
end

When(/^Card page starts$/) do
  @main_page.filter_action_should_appear
end
