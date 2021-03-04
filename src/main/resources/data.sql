
INSERT INTO recipe(id,title,description,preparation_time,portions,category,count_like)
VALUES
(1, 'Tortilla de Patatas (Potato Omelette)', 'Add olive oil to a 10 or 12 inch skillet over medium heat. (It’s best to use a skillet that is at least 1 1/2 inches deep.) Add sliced potato and onion to the pan; they should be mostly covered with olive oil (add a little more oil if needed). Season with 1 1/2 teaspoon sea salt. Cook on medium-high heat, maintaining a gentle boil, for 8-12 minutes, turning occasionally, until potatoes are just fork tender. Don’t overcook them! Drain potatoes, reserving oil for later use.','60',5,'SUPPER',1),
(2, 'Sangria', 'Chop your fruit: Dice the orange, lemon and green apple into evenly-sized pieces. Stir everything together: Combine the diced fruit, wine, brandy, the juice of one orange, and a cinnamon stick together in a large pitcher. Cover and refrigerate: Pop the pitcher in the fridge for at least 30 minutes or up to 4 hours before serving, in order to let those flavors meld together.','30',4,'DRINK',0),
(3, 'Gazpacho ', 'Combine all of your gazpacho ingredients in a blender or food processor, and puree for 1 minute or until the gazpacho reaches your desired consistency.  (I love mine super-smooth.) Transfer the soup to a sealed container and refrigerate for 4 hours or until completely chilled. Then serve the soup nice and cold, garnished with your favorite toppings.','40',2,'DINNER',2);

INSERT INTO ingredient(id, name, quantity, recipe_id)
VALUES
(1, 'Potato','0,5 kg',1),
(2, 'Onion','2',1),
(3, 'Red wine','750 ml',2),
(4, 'Apple','1/2',2),
(5, 'Orange','1/2',2),
(6, 'Orange juice','3/4 cup',2),
(7, 'Ice','1 cup',2);

INSERT INTO user(id, email, password)
VALUES (1, 'admin123@byom.de', '{noop}a'), (2, 'pat@byom.de', '{noop}a');

INSERT INTO user_role(user_id, role)
VALUES (1, 'ROLE_ADMIN'), (1, 'ROLE_USER'), (2, 'ROLE_USER')
