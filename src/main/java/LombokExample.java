import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Using annotation @Data will make it even easier.
 *
 * \@Data is like having implicit @Getter, @Setter, @ToString, @EqualsAndHashCode
 * and @RequiredArgsConstructor annotations on the class (except that no constructor
 * will be generated if any explicitly written constructors already exist).
 *
 * Check documentation: https://projectlombok.org/features/all
 *
 * In settings search for "annotation processor" and enable it.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LombokExample {
    @Getter @Setter int field1;
    @Getter @Setter int field2;
    @Getter @Setter int field3;

    /**
     * Showcases.
     * @param args Args
     */

    public static void main(String[] args) {
        // There is a constructor without argument.
        LombokExample lombok = new LombokExample();
        System.out.println(lombok.toString());

        // There is a constructor with all fields.
        LombokExample lombokWithField = new LombokExample(1, 2, 3);

        // There's an equals method.
        LombokExample lombokWithField2 = new LombokExample(1, 2, 3);
        System.out.println(lombokWithField.equals(lombokWithField2));

        // There's a toString method.
        System.out.println(lombokWithField.toString());
    }
}
