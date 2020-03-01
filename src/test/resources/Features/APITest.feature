Feature: A short description of the desired functionality

#  Scenario Outline: Add Employee record
#    Given User enter number "<a>" and number "<b>"
#    When User give operation "<method>"
#    Then I receive valid Response as output as "<output>"
#    Examples:
#      | a  | b  | method | output |
#      | 2  | 4  | add    | 6      |
#      | 10 | 20 | add    | 30     |

  Scenario Outline: Compare two file objects
    Given Load response from param "<param>" and value "<value1>"
    And  Load response from param "<param>" and value "<value2>"
    And Compare both the responses line by line
    Examples:
      | param | value1 | value2 |
      | page  | 1      | 2      |
      | page  | 1      | 1      |
