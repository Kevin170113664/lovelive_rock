require 'calabash-android/calabash_steps'

When /^I click card navigator$/ do
  @main_page.click_card_navigator
end

When(/^I should see Card$/) do
  wait_for_text('Card', timeout: 10)
end

Then /^I can click filter action$/ do
  @main_page.click_filter_action
end
