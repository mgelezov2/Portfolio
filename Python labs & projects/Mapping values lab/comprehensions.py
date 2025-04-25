
employee_list = [
   {"id": 12345, "name": "John", "department": "Kitchen"},
   {"id": 12456, "name": "Paul", "department": "House Floor"},
   {"id": 12478, "name": "Sarah", "department": "Management"},
   {"id": 12434, "name": "Lisa", "department": "Cold Storage"},
   {"id": 12483, "name": "Ryan", "department": "Inventory Mgmt"},
   {"id": 12419, "name": "Gill", "department": "Cashier"}
]


def mod(employee_list):
   temp = employee_list['name'] + "_" + employee_list["department"]
   return temp

def to_mod_list(employee_list):

   # ATTEMPT 1 (Failed)

   # employee_elements = map(mod, employee_list)

   # for elem_list in elem_list in employee_elements:

   # return employee_elements

   # ATTEMPT 2 (Success)

   employee_elements = map(mod, employee_list)

   employee_output = [elem_list for elem_list in employee_elements]

   return employee_output

   raise NotImplementedError()

def generate_usernames(mod_list):

   # ATTEMPT 1 (Failed)

   # username_list = [space_replacement.replace(" ", "_") generate_usernames for employee_list in employee_list]

   # space_replacement.replace(" ", "_")

   # return username_list

#==================================================================================================

   # ATTEMPT 2 (Failed)

   # username_list = [space_replacement.replace(" ", "_") for space_replacement in employee_list]

   # return username_list

#==================================================================================================

   # ATTEMPT 3 (Success)

   username_list = [space_replacement.replace(" ", "_") for space_replacement in mod_list]

   return username_list

   raise NotImplementedError()

def map_id_to_initial(employee_list):

   # ATTEMPT 1 (Failed)

   # name_dict = {}

   # for employee_list:"name" for [0] in employee_list

#============================================================

   # ATTEMPT 2 (Failed)

   # id_by_letter_dict = {employee_list:"name" for [0] in employee_list}

#============================================================

   # ATTEMPT 3 (Success)

   id_by_letter_dict = {name_id['name'][0]:name_id['id'] for name_id in employee_list}

   return id_by_letter_dict

   raise NotImplementedError()

def main():
   mod_emp_list = to_mod_list(employee_list)
   print("Modified employee list: " + str(mod_emp_list) + "\n")

   print(f"List of usernames: {generate_usernames(mod_emp_list)}\n")

   print(f"Initials and ids: {map_id_to_initial(employee_list)}")

if __name__ == "__main__":
   main()
