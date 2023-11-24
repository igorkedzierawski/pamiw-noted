package zet.kedzieri.noted;

import zet.kedzieri.noted.note.entity.Note;
import zet.kedzieri.noted.note.NoteRepository;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static void injectRandomNotes(NoteRepository repository, NotedUser author) {
        repository.saveAll(Arrays.stream(randomNotes(author)).toList());
    }

    public static Note[] randomNotes(NotedUser author) {
        Random random = new Random(author.getName().hashCode() + author.getSurname().hashCode() + author.getUsername().hashCode() + author.getId());

        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < notesData.length; i++) {
            ints.add(i);
        }

        Collections.shuffle(ints);

        int len = random.nextInt(notesData.length / 3) + 4;
        Note[] notes = new Note[len];
        for (int i = 0; i < len; i++) {
            String[] tc = notesData[i];
            notes[i] = createRandomNote(author, tc[0], tc[1], random);
        }

        return notes;
    }

    // Method to create a new Note with random createdAt and editedAt
    public static Note createRandomNote(NotedUser author, String title, String content, Random random) {
        // Generate random LocalDateTime for createdAt and editedAt
        LocalDateTime createdAt = getRandomDateTime(random);
        LocalDateTime editedAt = getRandomDateTime(random);

        // Create and return a new Note with the provided parameters and random timestamps
        return new Note(author, title, content, createdAt, random.nextBoolean() ? null : editedAt);
    }

    private static LocalDateTime getRandomDateTime(Random random) {
        // Define a range for random date (e.g., last year to now)
        LocalDateTime startDate = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
        LocalDateTime endDate = LocalDateTime.now();

        long startEpochDay = startDate.toLocalDate().toEpochDay();
        long endEpochDay = endDate.toLocalDate().toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        LocalDateTime randomDateTime = LocalDateTime.ofEpochSecond(randomEpochDay * 24 * 60 * 60, 0, ZoneOffset.UTC);

        // You can also add random hours, minutes, and seconds if needed
        randomDateTime = randomDateTime.plusHours(random.nextInt(24))
                .plusMinutes(random.nextInt(60))
                .plusSeconds(random.nextInt(60));

        return randomDateTime;
    }

    public static String[][] notesData = {
            {"Meeting with Team", "Discussed project updates and upcoming deadlines."},
            {"Shopping List", "Milk, eggs, bread, and coffee."},
            {"Book Recommendation", "Just finished reading 'The Silent Patient' - a gripping psychological thriller."},
            {"Fitness Goals", "Completed a 5K run today. Feeling accomplished!"},
            {"Recipe Idea", "Thinking of trying a new pasta recipe with tomatoes and basil."},
            {"Travel Plans", "Considering a trip to the mountains next month."},
            {"Coding Challenge", "Solved a challenging coding problem on LeetCode."},
            {"Movie Night", "Watched 'Inception' last night. Mind-blowing plot!"},
            {"Gardening Update", "Planted new flowers in the backyard."},
            {"Tech Gadgets Wishlist", "Looking into the latest smartphones and smartwatches."},
            {"Journaling Reflection", "Grateful for the little moments of joy in everyday life."},
            {"Home Improvement", "Started remodeling the living room."},
            {"Learning Spanish", "Practiced new vocabulary words today."},
            {"Photography Session", "Captured the beautiful sunset at the beach."},
            {"Favorite Quotes", "“The only limit to our realization of tomorrow will be our doubts of today.” - Franklin D. Roosevelt"},
            {"Hiking Adventure", "Explored a new trail in the woods."},
            {"DIY Project", "Crafting a handmade birthday gift for a friend."},
            {"New Music Discovery", "Discovered a great indie band - their music is so refreshing!"},
            {"Mindfulness Practice", "Took a moment to meditate and focus on the present."},
            {"Career Goals", "Setting milestones for professional growth."},

            {"Reflections on a Productive Day",
                    "Today was incredibly productive! Started the morning with a workout, followed by a healthy breakfast. " +
                            "Worked on the new project proposal and made significant progress. Had a fruitful meeting with the team, " +
                            "discussing strategies for the upcoming sprint. Excited about what we can achieve together."},

            {"Travel Journal: Exploring the City of Lights",
                    "Paris, the city of lights, has been an absolute dream! Visited the iconic Eiffel Tower and enjoyed " +
                            "breathtaking views of the city. Explored charming cafes in Montmartre, indulging in delicious croissants. " +
                            "Every corner reveals a piece of history and art. Grateful for this unforgettable experience."},

            {"Recipe Experiment: Mediterranean Feast",
                    "Spent the afternoon experimenting with a Mediterranean-inspired feast. The kitchen was filled with " +
                            "aromas of garlic, olive oil, and fresh herbs. Tried my hand at homemade tzatziki and grilled lemon herb chicken. " +
                            "The flavors transported me to the shores of the Mediterranean. Can't wait to share these recipes!"},

            {"Deep Dive into Astrophysics",
                    "Dedicated the evening to dive into astrophysics. Fascinated by the mysteries of the universe, " +
                            "I explored concepts of black holes, dark matter, and the expanding cosmos. The more I learn, the more " +
                            "awe-inspiring the universe becomes. Science is truly a never-ending adventure."},

            {"Weekend Retreat in the Countryside",
                    "Escaped the hustle and bustle for a weekend retreat in the countryside. The serene landscapes, " +
                            "rustling leaves, and the sound of a distant stream created a perfect ambiance for relaxation. " +
                            "Enjoyed quiet walks, stargazing, and reconnecting with nature's tranquility."},

            {"Book Club Meeting: 'The Night Circus'",
                    "Engaged in a thought-provoking discussion at the book club meeting. Explored the enchanting world " +
                            "of 'The Night Circus.' The intricate plot, magical ambiance, and well-developed characters left us " +
                            "spellbound. Looking forward to our next literary adventure."},

            {"Fitness Journey Milestone",
                    "Celebrating a fitness milestone today! After weeks of consistency, reached a new personal best in " +
                            "both running distance and weightlifting. The journey is challenging but immensely rewarding. " +
                            "Grateful for the strength, both physical and mental, that I've discovered along the way."},

            {"Artistic Exploration: Painting with Acrylics",
                    "Embarked on an artistic journey today, experimenting with acrylic paints. The canvas became a " +
                            "playground of colors and emotions. Each brushstroke told a story, and the final piece reflects " +
                            "a fusion of inspiration and self-expression. Art truly is a therapeutic escape."},

            {"Tech Innovations Symposium",
                    "Attended a fascinating symposium on the latest technological innovations. Explored advancements " +
                            "in artificial intelligence, quantum computing, and sustainable tech solutions. The future is " +
                            "incredibly exciting, with limitless possibilities for positive impact."},

            {"Mindfulness and Meditation Retreat",
                    "Participated in a mindfulness and meditation retreat. The serene setting, guided meditation sessions, " +
                            "and mindful practices provided a much-needed break from the chaos of daily life. Feeling rejuvenated " +
                            "and ready to approach challenges with a calm and focused mind."},

            {"Epic Movie Marathon Weekend",
                    "Dedicated the entire weekend to an epic movie marathon! Explored classics, binge-watched " +
                            "series, and discovered hidden gems. The cinematic journey was a delightful escape into different worlds. " +
                            "Movies have a unique way of inspiring, entertaining, and provoking thought."},

            {"Gourmet Cooking Class Experience",
                    "Immersed myself in a gourmet cooking class today. From mastering intricate techniques to " +
                            "creating visually stunning dishes, the experience was both educational and delicious. Can't wait to " +
                            "impress friends and family with these culinary delights!"},

            {"Exploring Ancient History",
                    "Dove into the pages of ancient history books today. Explored civilizations, myths, and cultural " +
                            "evolutions that shaped our world. The rich tapestry of human history is both captivating and " +
                            "profoundly impactful."},

            {"DIY Home Decor Project",
                    "Spent the weekend unleashing creativity on a DIY home decor project. From repurposing furniture " +
                            "to crafting personalized wall art, every piece tells a story of imagination and innovation. " +
                            "Home is where creativity flourishes."},

            {"Entrepreneurial Insights Symposium",
                    "Attended a thought-provoking symposium on entrepreneurial insights. Listened to successful " +
                            "entrepreneurs share their journeys, challenges, and strategies for building impactful businesses. " +
                            "Feeling inspired to pursue entrepreneurial endeavors with passion and purpose."},

            {"Nature Photography Expedition",
                    "Embarked on a nature photography expedition. Captured the beauty of landscapes, flora, and fauna. " +
                            "Photography is a powerful tool for preserving and sharing the wonders of the natural world."},

            {"Reflecting on Personal Growth",
                    "Took a moment to reflect on personal growth and self-discovery. Recognized the resilience gained " +
                            "through challenges, the wisdom acquired through experiences, and the continuous journey of becoming the " +
                            "best version of oneself."},

            {"Musical Exploration: Jazz Night",
                    "Immersed myself in the soulful tunes of a jazz night. From improvisation to intricate melodies, " +
                            "jazz music has a unique ability to convey emotion and tell stories. A truly enriching and immersive " +
                            "musical experience."},

            {"Starry Night Camping Adventure",
                    "Spent a magical night camping under the stars. The crisp night air, crackling campfire, and " +
                            "unobstructed view of the night sky created an enchanting atmosphere. Nature's wonders never cease " +
                            "to inspire awe and gratitude."},

            {"Tech for Social Good Symposium",
                    "Attended a symposium focusing on technology for social good. Explored innovations addressing " +
                            "global challenges, from healthcare to environmental sustainability. Technology has the power to " +
                            "create positive change, and these initiatives are truly inspiring."},


            {"A Day of Self-Reflection",
                    "Today, I took a pause to reflect on my journey. The morning began with a serene sunrise, casting a warm glow " +
                            "across the room. Over a cup of herbal tea, I delved into introspection, revisiting dreams, aspirations, and " +
                            "personal growth. It was a therapeutic journey, paving the way for mindful living and gratitude."},

            {"Adventures in Culinary Exploration",
                    "Spent the day experimenting with diverse flavors in the kitchen. From the tantalizing aroma of spices " +
                            "to the sizzle of ingredients on the pan, each moment was a dance of culinary creativity. The highlight was " +
                            "a fusion dish blending cultural influences, a celebration of diversity on a plate."},

            {"Journey Through Literary Realms",
                    "Immersed myself in the enchanting world of literature today. The pages of a captivating novel transported " +
                            "me to distant lands, allowing me to walk in the shoes of fascinating characters. The power of storytelling is " +
                            "undeniably magical, offering both escape and insight."},

            {"Serendipitous Encounter with Art",
                    "Wandered into a local art gallery and stumbled upon a hidden gem. Each stroke of the artist's brush " +
                            "conveyed emotions and narratives, making the canvas come alive. Art has a profound ability to evoke feelings " +
                            "and stir the soul."},

            {"Nature's Symphony in the Countryside",
                    "Escaped to the countryside, where nature orchestrated a symphony of sounds and colors. The rustling leaves, " +
                            "singing birds, and the gentle flow of a nearby stream created a harmonious melody. In these moments, the " +
                            "natural world becomes a canvas of serenity."},

            {"Quest for Knowledge: Astronomy Deep Dive",
                    "Embarked on a journey through the cosmos today. From the formation of galaxies to the intricacies of " +
                            "stellar nurseries, each discovery in the realm of astronomy unveiled the grandeur of the universe. The night " +
                            "sky became a captivating storybook of celestial wonders."},

            {"Theatrical Odyssey: A Night at the Opera",
                    "Indulged in a cultural escapade at the opera house. The opulent setting, the dramatic performances, " +
                            "and the powerful crescendos stirred a range of emotions. The performing arts have an unparalleled ability " +
                            "to convey stories that resonate deep within the soul."},

            {"Soulful Sojourn into Jazz",
                    "Dedicated an evening to the soulful melodies of jazz. The improvisations, intricate harmonies, and " +
                            "rhythmic expressions created an immersive experience. Jazz, with its spontaneity and emotional depth, " +
                            "continues to be a source of artistic inspiration."},

            {"Rediscovering Old Classics",
                    "Spent a lazy Sunday rediscovering classic literature. Each page turned was a journey into the minds " +
                            "of literary giants, exploring timeless themes and universal truths. The classics, like old friends, offer " +
                            "comfort and enduring wisdom."},

            {"Fitness Triumphs: Conquering New Heights",
                    "Celebrating a milestone in my fitness journey today. Climbed a challenging peak, basking in the " +
                            "glory of both physical and mental endurance. The summit offered breathtaking views, a metaphorical reminder " +
                            "that challenges, when conquered, lead to new perspectives."},

            {"Contemplative Strolls in the Botanical Gardens",
                    "Found solace in the serenity of the botanical gardens. Amidst vibrant blooms and towering trees, " +
                            "contemplative strolls allowed moments of introspection. Nature, with its cycles of growth and decay, mirrored " +
                            "the profound dance of life."},

            {"A Week of Mindful Living",
                    "This week has been dedicated to embracing mindful living. Each day began with a session of guided meditation, " +
                            "setting a positive tone for the hours ahead. Mindfulness practices, from mindful breathing to being present in " +
                            "every moment, have brought a sense of calm and clarity to my daily routine.\n\n" +
                            "In the hustle and bustle of modern life, these mindful moments serve as anchors, reminding me to appreciate the " +
                            "beauty of simplicity and the richness of the present."},

            {"Culinary Adventure: Exploring Global Flavors",
                    "Embarked on a culinary adventure exploring diverse global flavors. From the spicy aromas of Indian spices to " +
                            "the subtle sweetness of Japanese desserts, the kitchen became a canvas of cultural exploration. Each recipe was a " +
                            "lesson in history and a celebration of the rich tapestry of global cuisine.\n\n" +
                            "Cooking, beyond being a necessity, is a journey of discovery and a feast for the senses."},

            {"Literary Escapade: A Journey Through Books",
                    "Dove into a literary escapade, navigating through the realms of fiction and non-fiction. The written word " +
                            "has a magical quality, transporting readers to different eras, distant lands, and the depths of imagination. " +
                            "Whether it's the pulse-quickening excitement of a thriller or the profound insights of a philosophical tome, " +
                            "books have the power to shape perspectives and ignite the imagination.\n\n" +
                            "In the company of books, time seems to suspend, and the possibilities are endless."},

            {"Artistic Expression: A Canvas of Emotions",
                    "Spent an entire day immersed in artistic expression. The canvas, adorned with colors and strokes, became a " +
                            "reflection of emotions and inner landscapes. The act of creating art is a deeply personal journey, a means of " +
                            "communicating feelings that words often fail to capture.\n\n" +
                            "Art, whether on canvas or in other forms, is a form of catharsis and a celebration of human expression."},

            {"Nature's Symphony: A Day in the Wilderness",
                    "Ventured into the wilderness for a day of solitude amidst nature's symphony. The rustling leaves, " +
                            "whispering winds, and the distant calls of birds created a harmonious melody. Nature has a way of grounding " +
                            "the soul and providing a respite from the noise of the outside world.\n\n" +
                            "In the embrace of nature, the mind finds clarity, and the heart finds peace."},

            {"Astrophysics Deep Dive: Exploring the Cosmos",
                    "Delved into the mysteries of the cosmos through an exploration of astrophysics. From the birth of stars " +
                            "to the enigma of dark matter, each concept unveiled a cosmic marvel. The vastness of the universe sparks " +
                            "wonder and curiosity, reminding us of the infinitesimal nature of our existence.\n\n" +
                            "In the study of the cosmos, science and imagination converge, opening doors to new realms of understanding."},

            {"Theatrical Soiree: A Night of Dramatic Arts",
                    "Attended a mesmerizing night of theatrical performances, where the stage came alive with drama and emotion. " +
                            "The actors, through their impeccable portrayals, transported the audience into the heart of each narrative. " +
                            "Theater is a celebration of the human experience, a mirror reflecting the intricacies of life and the human " +
                            "condition.\n\n" +
                            "In the dim glow of the stage lights, stories unfold, leaving an indelible mark on the hearts of those who witness them."},

            {"Musical Odyssey: A Journey Through Genres",
                    "Embarked on a musical odyssey, traversing through diverse genres and eras. From the soulful tunes of jazz " +
                            "to the rhythmic beats of world music, each note was a step in a sonic exploration. Music has the power to " +
                            "evoke emotions, conjure memories, and transcend the boundaries of language.\n\n" +
                            "In the world of music, diversity is not only celebrated but also essential for the enrichment of the auditory palette."},

            {"Historical Reverie: Discovering Ancient Civilizations",
                    "Spent a contemplative day delving into the annals of history, exploring the rise and fall of ancient " +
                            "civilizations. The remnants of bygone empires and the echoes of ancient cultures are windows into the " +
                            "intricate tapestry of human history. History, with its lessons and legacies, shapes the present and guides " +
                            "us towards the future.\n\n" +
                            "In the pages of history, the echoes of the past resonate, offering insights into the complexities of the human journey."},

            {"Fitness Chronicles: A Holistic Approach",
                    "Embarked on a fitness journey that goes beyond the physical aspects, embracing a holistic approach to " +
                            "well-being. From mindful yoga sessions to strength training, each exercise was a step towards a balanced " +
                            "and healthy lifestyle. Fitness, when approached with mindfulness, becomes a journey of self-care and " +
                            "nourishment.\n\n" +
                            "In the pursuit of fitness, the body and mind find synergy, fostering overall well-being and resilience."},

            {"Sculpting Dreams: A Day in the Art Studio",
                    "Spent a day in the art studio, sculpting dreams from malleable clay. Each creation took form under " +
                            "the skilled hands, a manifestation of imagination and artistic vision. Sculpting is a dance between " +
                            "mind and matter, where the tactile medium becomes a conduit for creative expression.\n\n" +
                            "In the silence of the studio, dreams take shape, and the artist becomes a storyteller through the language of form."},

            {"Tech Symposium: Unveiling Future Innovations",
                    "Participated in a symposium that unveiled the future of technology and innovation. From cutting-edge " +
                            "advancements in artificial intelligence to sustainable tech solutions, the symposium showcased the " +
                            "potential of human ingenuity. Technology, when wielded responsibly, becomes a force for positive change " +
                            "and progress.\n\n" +
                            "In the digital age, the synergy of human intellect and technological prowess shapes the landscape of possibilities."},

            {"Medieval Fantasy: A Day in the World of Knights",
                    "Embarked on a journey into medieval fantasy, immersing myself in tales of knights, castles, and mythical " +
                            "creatures. Each page turned was a portal into a world of chivalry and enchantment. Fantasy literature, " +
                            "with its vivid imagery and imaginative narratives, provides an escape into realms untethered by reality.\n\n" +
                            "In the realm of fantasy, the ordinary transforms into the extraordinary, and the imagination takes flight."},

            {"Environmental Odyssey: Exploring Ecosystems",
                    "Embarked on an environmental odyssey, exploring diverse ecosystems and the delicate balance of nature. " +
                            "From lush rainforests to arid deserts, each ecosystem tells a unique story of adaptation and coexistence. " +
                            "Environmental awareness becomes a call to action, urging us to protect and preserve the biodiversity of " +
                            "our planet.\n\n" +
                            "In the exploration of ecosystems, the intricate dance of life unfolds, showcasing the interconnectedness of all living beings."},

            {"Reflecting on Personal Growth: A Journey Within",
                    "Took a moment to reflect on personal growth, acknowledging the triumphs and lessons learned along the " +
                            "journey. Growth is not just about milestones; it's a continuous process of self-discovery and " +
                            "transformation. Each challenge navigated and every insight gained contributes to the evolving tapestry " +
                            "of the self.\n\n" +
                            "In the canvas of personal growth, resilience becomes brushstrokes, and self-awareness becomes the palette."},

            {"Dance of the Elements: Elemental Exploration",
                    "Explored the dance of the elements, contemplating the primal forces that shape our world. From the " +
                            "roaring waves of the ocean to the gentle rustle of leaves, each element has a story to tell. Elemental " +
                            "exploration fosters an appreciation for the beauty and dynamism inherent in the natural world.\n\n" +
                            "In the symphony of the elements, the Earth becomes a stage, and the dance unfolds in the intricate choreography of nature."},

            {"Cinematic Chronicles: A Deep Dive into Film",
                    "Dedicated a day to cinematic exploration, delving into the art of filmmaking. From classic masterpieces " +
                            "to contemporary gems, each film was a journey into storytelling through the lens. The language of cinema " +
                            "transcends barriers, offering a medium where emotions are raw, narratives are compelling, and the human " +
                            "experience is laid bare.\n\n" +
                            "In the world of cinema, storytelling becomes a visual symphony, leaving an indelible mark on the hearts of viewers."},

            {"Mindful Tech Usage: Balancing Digital Life",
                    "Embarked on a journey of mindful tech usage, aiming to strike a balance between the digital world and " +
                            "real-life experiences. From conscious social media engagement to intentional screen time, the goal is " +
                            "to harness technology as a tool for connection and enrichment. Mindful tech usage becomes a conscious " +
                            "choice, fostering a healthier relationship with the digital landscape.\n\n" +
                            "In the era of technology, mindfulness becomes the key to navigating the digital realm with intention and presence."},

            {"Artisanal Exploration: A Day in the Craft Studio",
                    "Spent a day exploring artisanal crafts in the studio, experimenting with various mediums and techniques. " +
                            "From pottery to handcrafted paper, each creation was a testament to the joy of hands-on creativity. " +
                            "Artisanal exploration provides a tangible connection to the creative process, where the maker becomes a " +
                            "weaver of stories and a creator of tangible beauty.\n\n" +
                            "In the craft studio, imagination meets skill, and the hands become conduits for artistic expression."}

    };

}
