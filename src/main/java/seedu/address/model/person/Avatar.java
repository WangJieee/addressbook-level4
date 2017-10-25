package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a Person's avatar in the address book.
 */

public class Avatar {
    public static final String MESSAGE_AVATAR_CONSTRAINTS =
            "The file path must be valid and the avatar should be of correct image file format";
    public static final String DEFAULT_PATH = "src/main/resources/images/default.png";
    private static final String AVATAR_VALIDATION_REGEX =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    public final String value;
    private BufferedImage avatar;

    /**
     * Sets avatar based on the given filepath.
     *
     * @throws IllegalValueException if the filepath is invalid.
     */
    public Avatar(String path) throws IllegalValueException, IOException {
        if (path == null) {
            File placeholder = new File(DEFAULT_PATH);
            this.avatar = ImageIO.read(placeholder);
        }
        else if (!isValidAvatar(path.trim())) {
            throw new IllegalValueException(MESSAGE_AVATAR_CONSTRAINTS);
        }
        else {
            try {
                File source = new File(path.trim());
                this.avatar = ImageIO.read(source);
            } catch (IOException e) {
                throw new IllegalValueException(MESSAGE_AVATAR_CONSTRAINTS);
            }
        }
        this.value = path;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    /**
     * Returns if a given string is a valid image file.
     */
    public static boolean isValidAvatar(String test) {
        return test.matches(AVATAR_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
